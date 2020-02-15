import { Component, OnInit } from '@angular/core';
import { Mutante } from 'src/app/modelo/mutante';
import { MutanteService } from 'src/app/servicios/mutante.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-elemento',
  templateUrl: './elemento.component.html',
  styles: []
})
export class ElementoComponent implements OnInit {

  ente: Mutante = {
    id: 0,
    nombre: "",
    dna1: "",
    dna2: "",
    dna3: "",
    dna4: "",
    dna5: "",
    dna6: "",
    clasificacion:""
  };

  constructor(private service: MutanteService, private actRoute: ActivatedRoute, private router: Router) { 
   this.actRoute.params.subscribe((data)=>{
     if(data['id'] != "nuevo"){
      this.getOne(data['id']);   
     }else{
      
    }
   });
  }

  save() {
    this.actRoute.params.subscribe((data) => {
      if (data['id'] == "nuevo") {
        this.add();
        
      } else {
        this.update(data['id']);
      }
     
    });
  }


  ngOnInit() {
  }

  add() {
    this.service.post(this.ente).subscribe((data) => {
      this.router.navigate(["/"]);
      alert(`Se acaba de agregar un registro en nuestro banco de ADN. \nA continuacion en la tabla podrá consultar si es Humano o Mutante`);
    });
  }

  update(id: number) {
    this.service.put(id, this.ente).subscribe((data) => {
      this.router.navigate(["/"]);
    }
    );
  }

  getOne(id: number) {
    this.service.getOne(id).subscribe((data) => {
      this.ente = data;
    })
  }
}

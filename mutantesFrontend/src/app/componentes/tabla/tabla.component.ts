import { Component, OnInit } from '@angular/core';
import { MutanteService } from 'src/app/servicios/mutante.service';
import { Mutante } from 'src/app/modelo/mutante';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styles: []
})
export class TablaComponent implements OnInit {

  entes:Mutante[] = [];
  consulta:string = "";

  constructor(private servicio:MutanteService, private router:Router) { 
    this.getAll();
    
  }

  ngOnInit() {
  }

  getAll(){
    this.servicio.getAll().subscribe((data)=>{
      this.entes = data;
    });   
  }
  stats(){
   this.servicio.stats().subscribe((data)=>{
     this.consulta = data;
   }); 
  }
  delete(id:number){
    this.servicio.delete(id).subscribe(
      ()=>{console.log("Registro eliminado."); window.location.reload();},
      ()=>{console.log("El registro no se pudo borrar.")}
    )
  }

  update(id:number){
    this.router.navigate(["ente/"+id]);
  }

  agregar(){
    this.router.navigate(["ente/nuevo"]);
  }
  
}

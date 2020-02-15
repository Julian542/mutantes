import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TablaComponent } from './componentes/tabla/tabla.component';
import { ElementoComponent } from './componentes/elemento/elemento.component';


const routes: Routes = [
  {path:'',component:TablaComponent},
  {path:'ente/:id', component:ElementoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

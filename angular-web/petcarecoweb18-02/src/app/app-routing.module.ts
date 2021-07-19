import { CadastroComponent } from './cadastro/cadastro.component';
import { LoginComponent } from './login/login.component';
import { SobrenosComponent } from './sobrenos/sobrenos.component';
import { SejaumheroiComponent } from './sejaumheroi/sejaumheroi.component';
import { DuvidasfrequentesComponent } from './duvidasfrequentes/duvidasfrequentes.component';
import { ContrateumservicoComponent } from './contrateumservico/contrateumservico.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { SejaumcuidadorComponent } from './sejaumcuidador/sejaumcuidador.component';


const routes: Routes = [
  {path: 'sejaumcuidador', component: SejaumcuidadorComponent},
  {path: 'home', component: HomepageComponent},
  {path: 'contrateumservico', component: ContrateumservicoComponent},
  {path: 'duvidasfrequentes', component: DuvidasfrequentesComponent},
  {path: 'sejaumheroi', component: SejaumheroiComponent},
  {path: 'sobrenos', component: SobrenosComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'cadastro', component: CadastroComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

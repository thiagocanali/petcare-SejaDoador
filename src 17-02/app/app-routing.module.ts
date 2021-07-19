import { SobrenosComponent } from './sobrenos/sobrenos.component';
import { SejaumheroiComponent } from './sejaumheroi/sejaumheroi.component';
import { DuvidasfrequentesComponent } from './duvidasfrequentes/duvidasfrequentes.component';
import { ContrateumservicoComponent } from './contrateumservico/contrateumservico.component';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { SejaumcuidadorComponent } from './sejaumcuidador/sejaumcuidador.component';
import { CadastroComponent } from './cadastro/cadastro.component';

const routes: Routes = [
  {path: 'sejaumcuidador', component: SejaumcuidadorComponent},
  {path: 'home', component: HomepageComponent},
  {path: 'contrateumservico', component: ContrateumservicoComponent},
  {path: 'duvidasfrequentes', component: DuvidasfrequentesComponent},
  {path: 'sejaumheroi', component: SejaumheroiComponent},
  {path: 'sobrenos', component: SobrenosComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: '', component: HomepageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

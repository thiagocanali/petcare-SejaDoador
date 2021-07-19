import { LoginComponent } from './login/login.component';
import { NgModule, Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import {SejaumcuidadorComponent} from './sejaumcuidador/sejaumcuidador.component';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomepageComponent } from './homepage/homepage.component';
import { ContrateumservicoComponent } from './contrateumservico/contrateumservico.component';
import { DuvidasfrequentesComponent } from './duvidasfrequentes/duvidasfrequentes.component';
import { SejaumheroiComponent } from './sejaumheroi/sejaumheroi.component';
import { SobrenosComponent } from './sobrenos/sobrenos.component';
import { CadastroComponent } from './cadastro/cadastro.component';

@NgModule({
  declarations: [
    AppComponent,
    SejaumcuidadorComponent,
    HomepageComponent,
    ContrateumservicoComponent,
    DuvidasfrequentesComponent,
    SejaumheroiComponent,
    SobrenosComponent,
    LoginComponent,
    CadastroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

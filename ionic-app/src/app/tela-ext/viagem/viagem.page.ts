
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viagem',
  templateUrl: './viagem.page.html',
  styleUrls: ['./viagem.page.scss'],
})
export class ViagemPage {

  constructor(private router: Router){}

  goTo(page: string){
    this.router.navigate([page])
  }
}


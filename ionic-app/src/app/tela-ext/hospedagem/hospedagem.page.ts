import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hospedagem',
  templateUrl: './hospedagem.page.html',
  styleUrls: ['./hospedagem.page.scss'],
})
export class HospedagemPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
goTo(){
  this.router.navigate(['hospedagem-services'])
}
}

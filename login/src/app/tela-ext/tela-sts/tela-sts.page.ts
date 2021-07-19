import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tela-sts',
  templateUrl: './tela-sts.page.html',
  styleUrls: ['./tela-sts.page.scss'],
})
export class TelaStsPage implements OnInit {
  
  slides = [
    {
      img: '../../../assets/imglogo/PetCareLogoSVG.svg',
      princ: 'Bem vindo',
      titulo: '3 passos para utilizar um serviço PetCare'
    },
    {
      princ: '1',
      titulo: 'Cadastro'
    },
    {
      princ: '2',
      titulo: 'Solicitar Serviço'
    },
    {
      princ: '3',
      titulo: 'Aguardar seu PetCare service',
      cadastro: 'Cadastrar-se'
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}


import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-passeio',
  templateUrl: './passeio.page.html',
  styleUrls: ['./passeio.page.scss'],
})
export class PasseioPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
goTo(){
  this.router.navigate(['passeio-services'])
}
}

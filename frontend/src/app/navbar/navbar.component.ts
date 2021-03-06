import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  token;
  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.token = this.tokenStorage.getToken();
  }

  logout() {
    this.tokenStorage.signOut();
    this.reloadPage();
    return this.router.navigateByUrl('/')
  }

  reloadPage() {
    window.location.reload();
  }

}

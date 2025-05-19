import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  accessToken: any;
  isAuthenticated: boolean = false;
  roles: any;
  username: any;
  
  // URL du backend - Assurez-vous d'utiliser le bon port
  private apiUrl = 'http://localhost:8081'; // Changé de 8081 à 8080

  constructor(private http: HttpClient, private router: Router) {
    // Vérifiez si un token existe déjà dans le localStorage au démarrage
    this.loadJwtTokenFromLocalStorage();
  }

  public login(username: string, password: string): Observable<any> {
    let options = {
      headers: new HttpHeaders().set("Content-Type", "application/x-www-form-urlencoded")
    };
    
    let params = new HttpParams()
      .set("username", username)
      .set("password", password);
    
    return this.http.post(`${this.apiUrl}/auth/login`, params, options)
      .pipe(
        tap((response: any) => {
          if (response && response['access-token']) {
            // Sauvegarder dans le localStorage
            window.localStorage.setItem("jwt-token", response['access-token']);
            this.loadProfile(response);
          }
        })
      );
  }

  loadProfile(data: any) {
    this.isAuthenticated = true;
    this.accessToken = data["access-token"];
    
    try {
      let decodeJwt: any = jwtDecode(this.accessToken);
      this.roles = decodeJwt.scope;
      this.username = decodeJwt.sub;
      console.log("Utilisateur authentifié:", this.username);
      console.log("Rôles:", this.roles);
    } catch (error) {
      console.error("Erreur de décodage du token JWT:", error);
      this.logout();
    }
  }

  logout() {
    this.isAuthenticated = false;
    this.accessToken = undefined;
    this.username = undefined;
    this.roles = undefined;
    // Supprimer du localStorage
    window.localStorage.removeItem("jwt-token");
    this.router.navigateByUrl("/login");
  }

  loadJwtTokenFromLocalStorage() {
    let token = window.localStorage.getItem("jwt-token");
    if (token) {
      try {
        this.accessToken = token;
        this.isAuthenticated = true;
        let decodeJwt: any = jwtDecode(token);
        this.roles = decodeJwt.scope;
        this.username = decodeJwt.sub;
        console.log("Token chargé depuis localStorage pour:", this.username);
        return true;
      } catch (error) {
        console.error("Token invalide dans localStorage:", error);
        this.logout();
        return false;
      }
    }
    return false;
  }

  hasRole(role: string): boolean {
    return this.roles && this.roles.includes(role);
  }
}
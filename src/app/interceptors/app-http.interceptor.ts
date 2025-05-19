import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core'; 
import { AuthService } from '../services/auth-services.service';

export const appHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  
  // Utiliser uniquement accessToken car nous savons qu'il est initialisé depuis localStorage
  // lors de la création du service
  const token = AuthService.accessToken;
  
  if (token && !req.url.includes('/auth/login')) {
    console.log("Ajout du token d'autorisation à la requête:", req.url);
    const cloned = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });
    return next(cloned);
  }
  
  return next(req);
};
import { Routes } from '@angular/router';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { authenticationGuard } from './guards/authentication.guard';
import { NotAuthorizedComponent } from './components/not-authorized/not-authorized.component';
import { RemboursementService } from './services/remboursement.service';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: "/login", pathMatch: "full" },
  { 
     path: 'admin',
    component: AdminTemplateComponent,
    canActivate: [authenticationGuard],
    children: [ 
      { path: 'notAuthorized', component: NotAuthorizedComponent },
      {  path: 'remboursements', component: RemboursementService },
    ]
  }
];
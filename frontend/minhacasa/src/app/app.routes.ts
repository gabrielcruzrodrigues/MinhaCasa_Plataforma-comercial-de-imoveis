import { Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginpageComponent } from './components/loginpage/loginpage.component';
import { RegisterpageComponent } from './components/registerpage/registerpage.component';
import { LogoutComponent } from './components/layout/logout/logout.component';
import { CreateImmobileComponent } from './components/create-immobile/create-immobile.component';
import { ProfileComponent } from './components/profile/profile.component';

export const routes: Routes = [
    {
        path: '', component: HomepageComponent
    },
    {
        path: 'login', component: LoginpageComponent
    },
    {
        path: 'register', component: RegisterpageComponent
    },
    {
        path: 'logout', component: LogoutComponent
    },
    {
        path: 'create-immobile', component: CreateImmobileComponent
    },
    {
        path: 'profile', component: ProfileComponent
    }
];

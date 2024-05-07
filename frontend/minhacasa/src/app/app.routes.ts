import { Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginpageComponent } from './components/loginpage/loginpage.component';

export const routes: Routes = [
    {
        path: '', component: HomepageComponent
    },
    {
        path: 'login', component: LoginpageComponent
    }
];

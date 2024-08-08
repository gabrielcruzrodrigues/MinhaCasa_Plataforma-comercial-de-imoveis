import { Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginpageComponent } from './components/loginpage/loginpage.component';
import { LogoutComponent } from './components/layout/logout/logout.component';
import { CreateImmobileComponent } from './components/create-immobile/create-immobile.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ImmobileDetailsComponent } from './components/immobile-details/immobile-details.component';
import { SearchComponent } from './components/search/search.component';
import { ContactComponent } from './components/contact/contact.component';
import { PlansAndServicesComponent } from './components/plans-and-services/plans-and-services.component';
import { RegisterComponent } from './components/register/register.component';
import { FavoritesComponent } from './components/favorites/favorites.component';

export const routes: Routes = [
    {
        path: '', component: HomepageComponent
    },
    {
        path: 'login', component: LoginpageComponent
    },
    {
        path: 'logout', component: LogoutComponent
    },
    {
        path: 'register', component: RegisterComponent
    },
    {
        path: 'create-immobile', component: CreateImmobileComponent
    },
    {
        path: 'profile', component: ProfileComponent
    },
    {
        path: 'immobile/:id/:name/:seller-id', component: ImmobileDetailsComponent
    },
    {
        path: 'search', component: SearchComponent
    },
    {
        path: 'contact', component: ContactComponent
    },
    {
        path: 'plans-and-services', component: PlansAndServicesComponent
    }, 
    {
        path: 'favorites', component: FavoritesComponent
    }
]

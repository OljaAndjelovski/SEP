import { Routes } from '@angular/router';
import { ChoosePaymentPageComponent } from '../choose-payment-page/choose-payment-page.component';
import { SubscribeMagazinePageComponent } from '../subscribe-magazine-page/subscribe-magazine-page.component';
import { SuccessPageComponent } from '../end-pages/success-page/success-page.component';
import { ErrorPageComponent } from '../end-pages/error-page/error-page.component';
import { CancelPageComponent } from '../end-pages/cancel-page/cancel-page.component';
import { PageNotFoundComponent } from '../end-pages/page-not-found/page-not-found.component';

export const routes: Routes = [
    {
        path: 'choose-payment',
        component: ChoosePaymentPageComponent
    },
    {
        path: 'subscribe-magazine',
        component: SubscribeMagazinePageComponent
    },
    {
        path: 'success',
        component: SuccessPageComponent
    },
    {
        path: 'error',
        component: ErrorPageComponent
    },
    {
        path: 'cancel',
        component: CancelPageComponent
    },
    {
        path: 'not-found',
        component: PageNotFoundComponent
    },
    {
        path: '',
        redirectTo: '/choose-payment',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/not-found',
        pathMatch: 'full'
    }
];
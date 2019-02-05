import { Routes } from '@angular/router';
import { ChoosePaymentPageComponent } from '../choose-payment-page/choose-payment-page.component';
import { SubscribeMagazinePageComponent } from '../subscribe-magazine-page/subscribe-magazine-page.component';
import { SuccessPageComponent } from '../end-pages/success-page/success-page.component';
import { ErrorPageComponent } from '../end-pages/error-page/error-page.component';
import { CancelPageComponent } from '../end-pages/cancel-page/cancel-page.component';
import { PageNotFoundComponent } from '../end-pages/page-not-found/page-not-found.component';
import { StartPageComponent } from '../end-pages/start-page/start-page.component';
import { SubscribeComponent } from '../subscribe/subscribe.component';
import { ConfirmSubscriptionComponent } from '../end-pages/confirm-subscription/confirm-subscription.component';

export const routes: Routes = [
    {
        path: 'choose-payment/:id',
        component: ChoosePaymentPageComponent
    },
    {
        path: 'magazines',
        component: SubscribeMagazinePageComponent
    },
    {
        path: 'subscribe',
        component: SubscribeComponent
    },
    {
        path: 'success',
        component: SuccessPageComponent
    },
    {
        path: 'confirm',
        component: ConfirmSubscriptionComponent
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
        path: 'home',
        component: StartPageComponent
    },
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/not-found',
        pathMatch: 'full'
    }
];
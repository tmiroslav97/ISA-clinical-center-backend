import React from 'react';
import ReactDOM from 'react-dom';
import { Router } from "react-router-dom";
import { Provider } from 'react-redux'
import './index.css';
import NavBar from './components/NavBar';
import InformationToasts from './components/InformationToasts';
import { ToastProvider } from 'react-toast-notifications';
import AppRouter from './AppRouter';
import { createBrowserHistory } from 'history'
import Footer from './components/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import store from './store';

export const history = createBrowserHistory();

//<ToastProvider>
//<InformationToasts />
//</ToastProvider>

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>
            <NavBar />
            <AppRouter />
            <ToastProvider>
                <InformationToasts />
            </ToastProvider>
            <Footer />
        </Router>
    </Provider>
    ,
    document.getElementById('root')
);

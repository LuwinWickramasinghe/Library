import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { Footer } from './layouts/NavbarAndFooter/Footer';
import { HomePage } from './layouts/Homepage/HomePage';
import { SearchBooksPage } from './layouts/SearchBooksPage/SearchBooksPage';
import { Redirect, Route, Switch } from 'react-router-dom';
import { BookCheckoutPage } from './layouts/BookCheckoutPage/BookCheckoutPage';
import LoginButton from './Auth/authLogin';
import AuthProfile from './Auth/authProfile';
import LogoutButton from './Auth/authLogout';

export const App = () => {
  return (
    <div className='d-flex flex-column min-vh-100'>
      <Navbar/>
      <AuthProfile/>

      <div className="flex-grow-1">

      <Switch>
      <Route path='/login'>
        <LoginButton/>
      </Route>
      
      <Route path='/logout'>
        <LogoutButton/>
      </Route>


        
     


      <Route path='/' exact>
        <Redirect to='/home'/>
      </Route>

      <Route path='/home'>
        <HomePage/>
      </Route>

      <Route path='/search'>
        <SearchBooksPage/>
      </Route>

      <Route path='/checkout/:bookId'>
        <BookCheckoutPage/>
      </Route>

      </Switch>
      </div>
      <Footer/>
    </div>
  );
}


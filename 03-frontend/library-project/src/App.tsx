import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { ExploreTopBooks } from './layouts/Homepage/ExploreTopBooks';
import { Carousel } from './layouts/Homepage/Carousel';
import { Heroes } from './layouts/Homepage/Heroes';
import { LibraryServices } from './layouts/Homepage/LibraryServices';

function App() {
  return (
    <div>
      <Navbar/>
      <ExploreTopBooks/>
      <Carousel/>
      <Heroes/>
      <LibraryServices/>
    </div>
  );
}

export default App;

import React from 'react';
import './styles.css'
import Header from './Header'
import Sidebar from './Sidebar'
import Main from './Main'
import Footer from './Footer'

class App extends React.Component {
    render() {
      return (
        <div className="container">
          <Header look="head"/>
          <Sidebar look="sidebar"/>
          <Main look="main"/>
          <Footer look="foot"/>
        </div>
      )
    }
}

export default App

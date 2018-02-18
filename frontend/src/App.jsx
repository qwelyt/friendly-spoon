import React from 'react';
import './styles.css'
import Header from './Header'
import Sidebar from './Sidebar'
import Main from './Main'
import Footer from './Footer'

class App extends React.Component {
  constructor(){
    super();
    this.state = {sidebarLook: 'sidebar'}
  }

  minimize(e){
    let l = this.state.sidebarLook === 'sidebar' ? 'sidebar-mini' : 'sidebar'
    this.setState({sidebarLook: l})
  }
  render() {
    const menu = [
      {
        name: "Home"
        ,link: "home"
        , icon: ""
      }
      , {
        name: "Wat"
        ,link: "wat"
        ,icon: ""
      }
      , {
         name: "Other"
         ,link: "other"
         ,icon: ""
       }
       , {
         name: "Someplace"
         ,link: "potatos"
         ,icon: ""
       }
     ]
     return (
       <div className="container">
         <Header look="head"/>
         <Sidebar look={this.state.sidebarLook} items={menu} minimize={this.minimize.bind(this)}/>
         <Main look="main"/>
         <Footer look="foot"/>
       </div>
     )
  }
}

export default App

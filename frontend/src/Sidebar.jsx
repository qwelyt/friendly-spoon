import React from 'react'

class Sidebar extends React.Component {

  render(){
    return (
      <div className={this.props.look}>
        Sidebar
        <nav>
          {this.props.items.map(i => {
            return <NavItem key={i.name} item={i} />
          })}
        </nav>
        <button onClick={this.props.minimize}>Minimize</button>
      </div>
    )
  }
}

class NavItem extends React.Component {
  render(){
    return (
      <div>
        <a href={this.props.item.link}>{this.props.item.name}</a>
      </div>
    )
  }
}

export default Sidebar

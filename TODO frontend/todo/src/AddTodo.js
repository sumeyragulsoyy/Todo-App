import React,{Component} from "react";

class AddTodo extends Component {
    state = {  
        name:''
    }

    handleChange= (e) =>{
        this.setState({
            name:e.target.value
        })
    }

    handleSubmit= (e) =>{
        e.preventDefault();
        this.props.addTodo(this.state);
        this.setState({
            name:''
        })
    }

    render() { 
        return ( 
            <div>
                <form onSubmit={this.handleSubmit} >
                    <label> Add new todo:</label>
                    <input type="text" onChange={this.handleChange} value={this.state.name}/>
                </form>

            </div>
         );
    }
}
 
export default AddTodo;
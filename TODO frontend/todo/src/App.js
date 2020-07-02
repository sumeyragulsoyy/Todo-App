import React ,{Component} from "react";
import Todos from "./Todos"
import AddTodo  from "./AddTodo"

class App extends Component {
    state = { 
        todos:[]
     }

     deleteTodo= (id) =>{
         const todos=this.state.todos.filter(todo=>{
             return todo.id !==id
         });
         this.setState({
             todos
         })
     }

     addTodo =(todo) => {
         todo.id=Math.random();
         let todos=[...this.state.todos,todo];
         this.setState(
             {todos}
         )
     }

     async componentDidMount(){
        const url="https://localhost:8080/users";
        const response = await fetch(url);
        const data =await response.json();
        console.log(data);
    }

    render() { 
        return (  
            <div className='todo-app container'>
                <h1 className="center blue-text">Todo's</h1>
                <AddTodo addTodo={this.addTodo} />
                <Todos todos={this.state.todos} deleteTodo={this.deleteTodo} />
                
            </div>
        );
    }
}
 
export default App;
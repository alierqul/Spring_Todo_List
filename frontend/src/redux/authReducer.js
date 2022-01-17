import * as ACTIONS from "./Constants";

const defaultState={
    isLoggedIn:false,
    username:undefined,
    name: undefined,
    image:undefined,
    password:undefined
  };
  
  const authReducer = ( state = {...defaultState } , action ) =>{
   if(action.type === ACTIONS.LOGOUT_SUCCESS){    
     return defaultState;
   }
   if(action.type === ACTIONS.LOGIN_SUCCESS){
    return {
      ...action.payload,
      isLoggedIn:true
    }
  }
    return state;
  };

  export default authReducer;
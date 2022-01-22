import axios
 from "axios";
export const registerUser = body =>{
   return axios.post('/api/1.0/users',body);
   
};

export const login = creds => {
   return axios.post('/api/1.0/auth', { },{auth: creds });
 };



 export const getUsers = (page=0,size=3) =>{
   return axios.get(`/api/1.0/users?page=${page}&size=${size}`);
}

export const setAuthorizationHeader = ({isLoggedIn, username, password }) => {
   console.log('isloggednin : '+isLoggedIn);
   if (isLoggedIn) {
     const authorizationHeaderValue = `Basic ${btoa(username + `:` + password)}`;
     axios.defaults.headers['Authorization'] = authorizationHeaderValue;
   } else {
     delete axios.defaults.headers['Authorization'];
   }
 };

 export const getMyTodoList = (username,page=0,size=3)=> {
  const path = username && `/api/1.0/${username}/todo?page=${page}&size=${size}`;  
  return axios.get(path);
 };

 export const saveTodo = (username,body)=> {
  const path = username && `/api/1.0/${username}/todo`;  
  return axios.post(path,body)
 };

export const changeLanguage = language => {
   axios.defaults.headers['accept-language'] = language;
 };


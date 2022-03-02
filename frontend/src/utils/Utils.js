import axios from 'axios';


export const formatDate = (date)=> {   
    
    return new Intl.DateTimeFormat(axios.defaults.headers['accept-language']    , {year: 'numeric', month: 'long',day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit'}).format(new Date(date));
   };
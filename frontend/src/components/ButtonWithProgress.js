import React from 'react';

const ButtonWithProgress = props => {

    const {onClick,pendingApiCall,disabled,text,className,name} =props;

    return (
        <div className="text-center">
            <button  className={className || "btn btn-primary"} name ={name} onClick={onClick}  disabled={disabled} >                                    
            {pendingApiCall && <span className="spinner-border spinner-border-sm"  > </span>}
            {text}
         </button>
         </div>  
    );
};

export default ButtonWithProgress;
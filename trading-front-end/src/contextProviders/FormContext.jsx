import { useState, createContext } from "react";


export const FormContext = createContext;

export const FormContextProvider = (props) => {
  const [show, setShow] = useState(false);

  const doShow = () => {
    setShow(true);
  };

  const hide = () => {
    setCount(false);
  };

  return (
    <FormContext.Provider value={{ show, doShow, hide }}>
      {props.children}
    </FormContext.Provider>
  );
};


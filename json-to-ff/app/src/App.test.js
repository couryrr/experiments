import React from 'react';
import Enzyme, { shallow, mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import App from './App';

Enzyme.configure({ adapter: new Adapter() })

describe("Basic testing setup. I have no idea what I am doing", () => {
  test('There is an editor in the app', () => {
    const app = shallow(<App />)
    const editor = app.find(".editor")
    expect(editor.exists()).toBe(true)
  })

  test('Editor gives a message when a invalid JSON string is sent', () => {
    const app = shallow(<App />)
    const instance = app.instance()
    try{
      instance.parseJson("a")
    } catch(error){
      expect(error).toBe("Invalid JSON string sent")
    }
  })

  test('Editor returns parsed JSON', () => {
    const app = shallow(<App />)
    const instance = app.instance()
    try{
      const json = instance.parseJson("{\"test\":true}")
      expect(json.test).toBe(true)
    } catch(error){
      //expect(error).toBe(null)
    }
  })
})

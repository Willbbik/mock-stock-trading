import { Route, Routes } from 'react-router-dom'
import HomePage from '@pages/home/HomePage'
import Main from './pages/Main';

function App() {

  return (
    <Routes>
      <Route path={"/"} element={<Main/>}>
        <Route index element={<HomePage/>}></Route>
      </Route>
    </Routes>
  )
}

export default App

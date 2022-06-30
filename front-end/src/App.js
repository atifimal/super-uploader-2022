import logo from './logo.svg';
import './App.css';
import UploadForm from './components/UploadForm';
import "bootstrap/dist/css/bootstrap.min.css";
import Header from './components/Header';

function App() {
  return (
    <div className='App'>
      <Header />
      <UploadForm />
    </div>
  );
}

export default App;

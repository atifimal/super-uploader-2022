import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FileList from './FileList';
import './UploadForm.css'

function UploadForm() {
    let file;
    let supportedTypes = 'image/png,image/jpeg,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';

    const fileChangedHandler = (event) => {
        file = event.target.files[0];
    };

    async function formDataPost() {
        if (await checkTheFile()) {
            let formData = new FormData();
            formData.append('file', file);
            let result = await fetch('http://127.0.0.1:8080/uploadFile', {
                method: 'POST',
                body: formData
            }).then((reponse) => {
                toast.success("Uploaded.");
            });
        }
    }

    function checkTheFile() {
        if (file.type === "" || !supportedTypes.includes(file.type)) {
            toast.error("Unsupported file type.");
            return false;
        }

        else if (file.size > 5 * 1024 * 1024) {
            toast.error("File size must not be > 5MB");
            return false;
        }

        else return true;
    }

    return (
        <div>
            <div className='info-buttons'>
                <a href='http://127.0.0.1:8080/swagger-ui.html'><button className='btn btn-dark'>Swagger-UI</button></a>
                <a href='http://127.0.0.1:8080/get'><button className='btn btn-dark'>Get All</button></a>
            </div>
            <form className="form-inline">
                <div className="form-group m-3">
                    <label htmlFor="formFile" className="form-label mx-3">File</label>
                    <input className="form-control d-inline w-auto" type="file" id="formFile" onChange={fileChangedHandler} />
                </div>
                <input type={"button"} className="btn btn-primary m-3" value="Send" onClick={formDataPost} />
            </form>
            <hr />
            <FileList />
            <ToastContainer />
        </div>
    )
}

export default UploadForm;
function UploadForm() {
    let file;
    const fileChangedHandler = (event) => {
        file = event.target.files[0];
        console.log(event.target.files[0]);
    };

    const formDataPost = () => {
        let formData = new FormData();
        formData.append('file', file);
        let result = fetch('http://localhost:8080/upload-file', {
            mode: 'no-cors',
            method: 'POST',
            body: formData
        });
    }

    return (
        <form className="form-inline">
            <div className="form-group m-3">
                <label htmlFor="formFile" className="form-label mx-3">File</label>
                <input className="form-control d-inline w-auto" type="file" id="formFile" onChange={fileChangedHandler} />
            </div>
            <input type={"button"} className="btn btn-primary m-3" value="Send" onClick={formDataPost} />
        </form>
    )
}

export default UploadForm;
function UploadForm() {
    const fileChangedHandler = (event) => {
        let file_size = event.target.files[0].size;
        let file_name = event.target.files[0].name;
        let file_type = event.target.files[0].type;
    
       console.log(event.target.files[0]);
    };

    return (
        <form className="form-inline" action="#" method="POST">
            <div className="form-group m-3">
                <label htmlFor="formFile" className="form-label mx-3">File</label>
                <input className="form-control d-inline w-auto" type="file" id="formFile" name="file" onChange={fileChangedHandler}/>
            </div>
            <input type={"submit"} className="btn btn-primary m-3" value="Send"/>
        </form>
    )
}

export default UploadForm;
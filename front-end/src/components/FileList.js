import React, { Component } from "react";

export default class FileList extends React.Component {
    state = {
        loading: true,
        jsondata: null
    }

    async componentDidMount() {
        const url = 'http://127.0.0.1:8080/get';
        const response = await fetch(url);
        const data = await response.json();
        this.setState({
            loading: false,
            jsondata: data
        })
    }

    deleteFile = (id) => {
        const url = 'http://127.0.0.1:8080/delete/' + id;
        fetch(url, {
            mode: "no-cors"
        });
    }

    render() {

        if (this.state.loading) {
            return (<div>Loading</div>);
        }

        if (!this.state.jsondata) {
            return (<div>No Data</div>);
        }

        return (
            <div>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>path</th>
                            <th>size</th>
                            <th>name</th>
                            <th>type</th>
                            <th>byte[]</th>
                            <th>delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.jsondata.map(file => (
                                <tr>
                                    <td>{file.id}</td>
                                    <td>{file.path}</td>
                                    <td>{file.size}</td>
                                    <td>{file.name}</td>
                                    <td>{file.contentType}</td>
                                    <td><a href={"http://127.0.0.1:8080/get/" + file.id} target="_blank"><button className="btn btn-success">Go</button></a></td>
                                    <td><a href={"http://127.0.0.1:8080/delete/" + file.id}><button className="btn btn-danger">Delete</button></a></td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        )
    }

}
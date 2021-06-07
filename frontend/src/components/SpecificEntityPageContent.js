import Grid from "@material-ui/core/Grid";
import {CardMedia, FormControl, InputLabel, MenuItem, Select, Tab, Tabs} from "@material-ui/core";
import Divider from "@material-ui/core/Divider";
import Paper from "@material-ui/core/Paper";
import React from "react";
import {STYLES} from "../STYLES";

import EntityProduct from "./EntityProduct";
import EntityHeader from "./EntityHeader";
import {API_CLIENT_BASE_URL} from "../environment";

// Future API call to retrieve this info


const entityHeaderInfo = {
    title: 'Farmácia Central',
    tax: 2.40,
    avgTime: '15-25 min',
    image: 'https://media-exp1.licdn.com/dms/image/C4D1BAQGmnJWzieLPIg/company-background_10000/0/1594146277424?e=2159024400&v=beta&t=mBixf88X92gFmvPD2gMpuG-GDBce0WOTiojTYSkAgVM'
};

const entityProductInfo = {
    title: 'Ben-U-Ron 500mg 20 Comprimidos',
    description: 'Loren ipsum loren ipsum loren ipsum loren ipsum loren.',
    price: 6.60,
    image: 'https://s1.kuantokusta.pt/img_upload/produtos_saudebeleza/191713_3_ben-u-ron-500mg-20-comprimidos.jpg'
};

/*
const classes = STYLES;
const [value, setValue] = React.useState(0);

const handleChange2 = (event, newValue) => {
    setValue(newValue);
}; */

export default class SpecificEntityPageContent extends React.Component {

    constructor() {
        super();
        this.state = {search_results: [], query: ""};
        this.setQuery = this.setQuery.bind(this);
        this.searchProduct = this.searchProduct.bind(this);

    }

    componentDidMount() {

    }

    searchProduct() {
        fetch(API_CLIENT_BASE_URL + 'search?query=' + this.state.query)
            .then(response => response.json())
            .then(json => {
                this.setState({search_results: json.data});
                console.log(this.state.search_results);
            });
    }

    setQuery(query_arg) {
        this.setState({query: query_arg})
    }


    render() {
        return (
            <div>

                <EntityHeader post={entityHeaderInfo}/>

                <br/>
                <Divider/>
                <br/>

                {/*

                <Paper className={classes.root}>
                    <Tabs
                        value={value}
                        onChange={handleChange2}
                        indicatorColor="primary"
                        textColor="primary"
                        centered
                    >
                        <Tab label="Ortopedia"/>
                        <Tab label="Higiene"/>
                        <Tab label="Sexualidade"/>
                        <Tab label="Categoria..."/>

                    </Tabs>
                </Paper>

                */}

                <br/>
                <Divider/>
                <br/>
                <div className="input-group">
                    <div className="form-outline">
                        <input type="search" onChange={event => this.setQuery(event.target.value)} id="form1"
                               className="form-control"/>
                    </div>
                    <button type="button" className="btn btn-primary" onClick={this.searchProduct}>
                        Search
                        <i className="fas fa-search"></i>
                    </button>
                </div>

                <br/>
                <Divider/>
                <br/>

                <Grid container item xs={12} spacing={3}>
                    {
                        this.state.search_results
                            ? 'Loading ...'
                            : this.state.search_results.map(data => (
                            <Grid item xs={4}>
                                <EntityProduct post={data}/>
                            </Grid>
                        ))
                    }


                </Grid>

            </div>
        );
    }


}

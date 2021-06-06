import Grid from "@material-ui/core/Grid";
import {CardMedia, FormControl, InputLabel, MenuItem, Select, Tab, Tabs} from "@material-ui/core";
import Divider from "@material-ui/core/Divider";
import Paper from "@material-ui/core/Paper";
import React from "react";
import {STYLES} from "../STYLES";

import EntityProduct from "./EntityProduct";
import EntityHeader from "./EntityHeader";

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

//

export default function SpecificEntityPageContent() {

    const classes = STYLES;
    const [value, setValue] = React.useState(0);

    const handleChange2 = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <div>
            
            <EntityHeader post={entityHeaderInfo}/>

            <br/>
            <Divider/>
            <br/>

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

            <br/>
            <Divider/>
            <br/>

            <Grid container item xs={12} spacing={3}>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
                <Grid item xs={4}>
                    <EntityProduct post={entityProductInfo}/>
                </Grid>
            </Grid>

        </div>
    );
}

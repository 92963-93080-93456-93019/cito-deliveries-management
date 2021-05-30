import Grid from "@material-ui/core/Grid";
import {CardMedia, FormControl, InputLabel, MenuItem, Select, Tab, Tabs} from "@material-ui/core";
import Divider from "@material-ui/core/Divider";
import Paper from "@material-ui/core/Paper";
import React from "react";
import {STYLES} from "./STYLES";

import FarmaciaComponent from "./FarmaciaComponent";



export default function MainPageContent() {

    const classes = STYLES;
    const [age, setAge] = React.useState('');
    const handleChange = (event) => {
        setAge(event.target.value);
    };

    const [value, setValue] = React.useState(0);

    const handleChange2 = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <div>
            <Grid container item sm={4} spacing={1}>
                <Grid item xs={4}>
                    <FormControl variant="filled" className={classes.formControl}>
                        <InputLabel id="demo-simple-select-filled-label">Ordenar</InputLabel>
                        <Select
                            labelId="demo-simple-select-filled-label"
                            id="demo-simple-select-filled"
                            value={age}
                            onChange={handleChange}>
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                            <MenuItem value={10}>Maior Preço</MenuItem>
                            <MenuItem value={20}>Menor Preço</MenuItem>
                            <MenuItem value={30}>Alfabética (a-z)</MenuItem>
                            <MenuItem value={30}>Alfabética (z-a)</MenuItem>
                        </Select>
                    </FormControl>
                </Grid>
                <Grid item sm={4}>
                    <FormControl className={classes.selectors} variant="filled" className={classes.formControl}>
                        <InputLabel id="demo-simple-select-filled-label">Taxa de Entrega</InputLabel>
                        <Select
                            labelId="demo-simple-select-filled-label"
                            id="demo-simple-select-filled"
                            value={age}
                            onChange={handleChange}
                        >
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                            <MenuItem value={10}> Menos de 2.5€</MenuItem>
                            <MenuItem value={20}>Menos de 3.5€</MenuItem>
                            <MenuItem value={30}>Menos de 5€</MenuItem>
                        </Select>
                    </FormControl>
                </Grid>
            </Grid>

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
                    <Tab label="Farmácias"/>
                    <Tab label="Ervanárias"/>
                    <Tab label="Drogarias"/>
                    <Tab label="..."/>

                </Tabs>
            </Paper>

            <br/>
            <Divider/>
            <br/>

            <Grid container item xs={12} spacing={3}>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
                <Grid item xs={4}>
                    <FarmaciaComponent></FarmaciaComponent>
                </Grid>
            </Grid>


        </div>
    );
}

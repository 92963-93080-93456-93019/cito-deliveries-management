import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import {CardMedia} from "@material-ui/core";
import farm1 from "../farm1.jpg";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import React from "react";
<<<<<<< HEAD:frontend/src/FarmaciaComponent.js
import {STYLES} from "./STYLES";
=======
import {STYLES} from "../STYLES";
import {useParams} from "react-router-dom"
>>>>>>> develop:frontend/src/components/FarmaciaComponent.js

export default function FarmaciaComponent() {
    const classes = STYLES;

    return (
        <Link href="/">
            <Card className={classes.root}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        alt="Contemplative Reptile"
                        height="150"
                        width="150"
                        image={farm1}
                        title="Contemplative Reptile"
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="h2">
                            Farmácia
                        </Typography>
                        <Typography variant="body2" color="textSecondary" component="p">
                            Taxa: 2,40€ 15-20 mins
                        </Typography>
                        <Typography variant="body2" color="textSecondary" component="p">
                            Espera: 15-20 mins
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </Link>
    );
}
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Link from "@material-ui/core/Link";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardMedia from '@material-ui/core/CardMedia';
import farm1 from "./farm1.jpg";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import React from "react";
//import {STYLES} from "./STYLES";

const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    },
    details: {
      display: 'flex',
      flexDirection: 'column',
    },
    content: {
      flex: '1 0 auto',
    },
    cover: {
      width: 151,
    },
    title: {
        fontSize: "medium"
    },
    description: {
        fontSize: "small"
    },
    price : {
        fontWeight: "bold"
    }
  }));

export default function EntityProduct(props) {

    const classes = useStyles();
    const theme = useTheme();
    const { post } = props;

    return (
        <Link href="/">
            <Card className={classes.root}>
            <div className={classes.details}>
                <CardContent className={classes.content}>
                    <Typography className={classes.title} component="h5" variant="h5">
                        {post.title}
                    </Typography>
                    <Typography className={classes.description} variant="subtitle1" color="textSecondary">
                        {post.description}
                    </Typography>
                    <Typography className={classes.price}>
                        {post.price}€
                    </Typography>
                </CardContent>
                <div className={classes.controls}>
                </div>
            </div>
            <CardMedia
                className={classes.cover}
                image={post.image}
                title={post.title}
            />
            </Card>
        </Link>
    );
}
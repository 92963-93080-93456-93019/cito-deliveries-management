import React from "react";
import clsx from "clsx";
import {makeStyles} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import Drawer from "@material-ui/core/Drawer";
import Box from "@material-ui/core/Box";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import List from "@material-ui/core/List";
import Typography from "@material-ui/core/Typography";
import Divider from "@material-ui/core/Divider";
import IconButton from "@material-ui/core/IconButton";
import Badge from "@material-ui/core/Badge";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import Link from "@material-ui/core/Link";
import MenuIcon from "@material-ui/icons/Menu";
import ChevronLeftIcon from "@material-ui/icons/ChevronLeft";
import NotificationsIcon from "@material-ui/icons/Notifications";
import {mainListItems} from "./listItems";
import InputBase from '@material-ui/core/InputBase';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';


import {
    Avatar,
    Button,
    CardMedia,
    FormControl,
    InputLabel, ListItem,
    ListItemAvatar,
    ListItemText,
    MenuItem,
    Select, Tab, Tabs
} from "@material-ui/core";
import farm1 from './farm1.jpg'
import avatar1 from './avatar1.jpeg'
import {STYLES} from "./STYLES";
import MainPageContent from "./MainPageContent";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright © "}
            <Link color="inherit" href="https://material-ui.com/">
                CITO
            </Link>{" "}
            {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}


const useStyles = STYLES;

export default function Dashboard() {
    const classes = useStyles();
    const [open, setOpen] = React.useState(true);
    const handleDrawerOpen = () => {
        setOpen(true);
    };
    const handleDrawerClose = () => {
        setOpen(false);
    };
    const fixedHeightPaper = clsx(classes.paper, classes.fixedHeight);

    return (
        <div className={classes.root}>
            <CssBaseline/>
            <AppBar
                position="absolute"
                className={clsx(classes.appBar, open && classes.appBarShift)}
            >
                <Toolbar className={classes.toolbar}>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        className={clsx(
                            classes.menuButton,
                            open && classes.menuButtonHidden
                        )}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Typography
                        component="h1"
                        variant="h6"
                        color="inherit"
                        noWrap
                        className={classes.title}
                    >
                        CITO
                    </Typography>

                    <InputBase
                        color="primary"
                        placeholder="Procurar..."
                        classes={{
                            root: classes.inputRoot,
                            input: classes.inputInput
                        }}
                        inputProps={{'aria-label': 'search'}
                        }
                    />

                    <IconButton color="inherit">
                        <Badge badgeContent={4} color="secondary">
                            <NotificationsIcon/>
                        </Badge>
                    </IconButton>
                </Toolbar>
            </AppBar>
            <Drawer
                variant="permanent"
                classes={{
                    paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
                }}
                open={open}>
                <div className={classes.toolbarIcon}>
                    <IconButton onClick={handleDrawerClose}>
                        <ChevronLeftIcon/>
                    </IconButton>
                </div>
                <Divider/>

                <div>

                    <List className={classes.root}>
                        <ListItem>
                            <ListItemAvatar alignItems="flex-start">
                                <Avatar className={classes.large} alt="Remy Sharp"
                                        src="https://material-ui.com/static/images/avatar/1.jpg"/>
                            </ListItemAvatar>
                            <ListItemText
                                primary="Alfredo"
                                secondary={
                                    <React.Fragment>
                                        <Typography component="span" variant="body2" className={classes.inline}
                                                    color="textPrimary">
                                        </Typography>
                                        {"alfredo@mail.com"}
                                    </React.Fragment>}
                            />
                        </ListItem>
                    </List>
                </div>

                <Divider/>
                {<List>{mainListItems}</List>}
                <Divider/>


            </Drawer>

            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <Container className={classes.container}>

                    <MainPageContent>

                    </MainPageContent>




                    <Box pt={4}>
                        <Copyright/>
                    </Box>
                </Container>
            </main>
        </div>
    );
}

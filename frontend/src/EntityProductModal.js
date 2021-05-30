import React from 'react';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import CardActions from '@material-ui/core/CardActions';
import Collapse from '@material-ui/core/Collapse';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import IconButton from '@material-ui/core/IconButton';
import clsx from 'clsx';
import Radio from '@material-ui/core/Radio';
import FormControl from '@material-ui/core/FormControl';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';

const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  cover: {
    width: "50%",
  },
  root: {
    display: 'flex',
    width: "70%",
    height: "500px"
  },
  details: {
    display: 'flex',
    flexDirection: 'row',
    flexWrap: "wrap"
  },
  content: {
    flex: '1 0 auto',
  },
  title: {
    fontSize: "xx-large"
},
expand: {
    transform: 'rotate(0deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest,
    }),
  },
  expandOpen: {
    transform: 'rotate(180deg)',
  },
}));

export default function EntityProductModal(props) {
  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);
  const { post } = props;
  const [expanded, setExpanded] = React.useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const [value, setValue] = React.useState('female');

  const handleChange = (event) => {
    setValue(event.target.value);
  };

  return (
    <div>
      <button type="button" onClick={handleOpen}>
        react-transition-group
      </button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
        <Card className={classes.root}>
            <CardMedia
                className={classes.cover}
                image={post.image}
                title={post.title}
            />
            <div className={classes.details}>
                <CardContent className={classes.content}>
                    <Typography className={classes.title} component="h5" variant="h5">
                        {post.title}
                    </Typography>
                    
                    <Card className={classes.details}>
                        
                        <CardContent>
                            <Typography variant="body2" color="textSecondary" component="p">
                            Select Option 1
                            </Typography>
                        </CardContent>

                        <CardActions disableSpacing>

                            <IconButton
                                className={clsx(classes.expand, {
                                    [classes.expandOpen]: expanded,
                                })}
                                onClick={handleExpandClick}
                                aria-expanded={expanded}
                                aria-label="show more"
                                >
                                <ExpandMoreIcon />
                            </IconButton>
                        </CardActions>
                        <Collapse in={expanded} timeout="auto" unmountOnExit>
                            <CardContent>
                            <FormControl component="fieldset">
                                {/* <FormLabel component="legend">Gender</FormLabel> */}
                                <RadioGroup aria-label="gender" name="gender1" value={value} onChange={handleChange}>
                                    <FormControlLabel value="sub1" control={<Radio />} label="Suboption 1" />
                                    <FormControlLabel value="sub2" control={<Radio />} label="Suboption 1" />
                                    <FormControlLabel value="sub3" control={<Radio />} label="Suboption 3" />
                                </RadioGroup>
                            </FormControl>
                                
                            </CardContent>
                        </Collapse>
                    </Card>

                </CardContent>
                <div className={classes.controls}>
                </div>
            </div>
            
            </Card>
        </Fade>
      </Modal>
    </div>
  );
}
import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';

import PaymentIcon from '@material-ui/icons/Payment';
import HelpIcon from '@material-ui/icons/Help';
import BarChartIcon from '@material-ui/icons/BarChart';

import ViewListIcon from '@material-ui/icons/ViewList';
import StarIcon from '@material-ui/icons/Star';
export const mainListItems = (
  <div>
    <ListItem button>
      <ListItemIcon>
        <ViewListIcon />
      </ListItemIcon>
      <ListItemText primary="Pedidos" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <StarIcon />
      </ListItemIcon>
      <ListItemText primary="Favoritos" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <PaymentIcon />
      </ListItemIcon>
      <ListItemText primary="Pagamento" />
    </ListItem>
    <ListItem button to="/asdasd">
      <ListItemIcon>
        <HelpIcon />
      </ListItemIcon>
      <ListItemText primary="Ajuda" />
    </ListItem>
  </div>

);

package edu.duke.ece651.mp.client;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import edu.duke.ece651.mp.common.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {
  public static int turnnum;
  // setting up FXML buttons
  @FXML
  public Button dragonstrikebutton;
  @FXML
  public Label dragonstrikecost;
  @FXML
  public Button finishbutton;
  @FXML
  public Label costlabel;
  @FXML
  public Label upgradecostlabel;
  @FXML
  public Button level0button;
  @FXML
  public Button level1button;
  @FXML
  public Button level2button;
  @FXML
  public Button level3button;
  @FXML
  public Button level4button;
  @FXML
  public Button level5button;
  @FXML
  public Label gondorsize;
  @FXML
  public Label midkemiasize;
  @FXML
  public Label hogwartssize;
  @FXML
  public Label ozsize;
  @FXML
  public Label rosharsize;
  @FXML
  public Label narniasize;
  @FXML
  public Label elantrissize;
  @FXML
  public Label scadrialsize;
  @FXML
  public Spinner<Integer> level0slider;
  @FXML
  public Spinner<Integer> level1slider;
  @FXML
  public Spinner<Integer> level2slider;
  @FXML
  public Spinner<Integer> level3slider;
  @FXML
  public Spinner<Integer> level4slider;
  @FXML
  public Spinner<Integer> level5slider;
  @FXML
  public Spinner<Integer> level6slider;
  @FXML
  public Label level0label;
  @FXML
  public Label level1label;
  @FXML
  public Label level2label;
  @FXML
  public Label level3label;
  @FXML
  public Label level4label;
  @FXML
  public Label level5label;
  @FXML
  public Label level6label;
  @FXML
  public Button submitbutton;

  @FXML
  public ImageView level0picture;
  @FXML
  public ImageView level1picture;
  @FXML
  public ImageView level2picture;
  @FXML
  public ImageView level3picture;
  @FXML
  public ImageView level4picture;
  @FXML
  public ImageView level5picture;
  @FXML
  public ImageView level6picture;
  @FXML
  public ImageView hogwartscolor;
  @FXML
  public ImageView midkemiacolor;
  @FXML
  public ImageView scadrialcolor;
  @FXML
  public ImageView gondorcolor;
  @FXML
  public ImageView elantriscolor;
  @FXML
  public ImageView ozcolor;
  @FXML
  public ImageView narniacolor;
  @FXML
  public ImageView rosharcolor;

  @FXML
  public Button level6button;
  @FXML
  public Button attackbutton;
  @FXML
  public TextArea activitylog;
  @FXML
  public Rectangle narnia;
  @FXML
  public Rectangle midkemia;
  @FXML
  public Rectangle oz;
  @FXML
  public Rectangle gondor;
  @FXML
  public Rectangle elantris;
  @FXML
  public Rectangle scadrial;
  @FXML
  public Rectangle roshar;
  @FXML
  public Rectangle hogwarts;

  @FXML
  public VBox cl_main;
  @FXML
  public Label playername;
  @FXML
  public Label playertechlevel;
  @FXML
  public Label playerresources;
  @FXML
  public Button upgradetlbutton;
  @FXML
  public Button upgradebutton;
  @FXML
  public Label level6label2;
  @FXML
  public Button donebutton;
  @FXML
  public Button movebutton;
  @FXML
  public Button selectactionbutton;
  @FXML
  public Button gondorbutton;
  @FXML
  public Button ozbutton;
  @FXML
  public Button midkemiabutton;
  @FXML
  public Button narniabutton;
  @FXML
  public Button cancelbutton;
  @FXML
  public Button showdatabutton;
  @FXML
  public Label promptlabel;
  @FXML
  public Label foodresources;
  @FXML
  public Label costlabel2;

  @FXML
  public Label midkemialabel;
  @FXML
  public Label hogwartslabel;
  @FXML
  public Label rosharlabel;
  @FXML
  public Label narnialabel;
  @FXML
  public Label scadriallabel;
  @FXML
  public Label gondorlabel;
  @FXML
  public Label elantrislabel;
  @FXML
  public Label ozlabel;
  @FXML
  public Label ozdatalabel;
  @FXML
  public Label midkemiadatalabel;
  @FXML
  public Label hogwartsdatalabel;
  @FXML
  public Label roshardatalabel;
  @FXML
  public Label narniadatalabel;
  @FXML
  public Label scadrialdatalabel;
  @FXML
  public Label gondordatalabel;
  @FXML
  public Label elantrisdatalabel;
  @FXML
  public Polygon elantrisselected;
  @FXML
  public Polygon gondorselected;
  @FXML
  public Polygon ozselected;
  @FXML
  public Polygon rosharselected;
  @FXML
  public Polygon hogwartsselected;
  @FXML
  public Polygon midkemiaselected;
  @FXML
  public Polygon narniaselected;
  @FXML
  public Polygon scadrialselected;

  public String to;
  public String from;
  public int tofromflag;
  public int attackmvflag;
  public int upgradeflag;
  public HashMap<String, Integer> units;

  public SpinnerValueFactory<Integer> valueFactory0;
  public SpinnerValueFactory<Integer> valueFactory1;
  public SpinnerValueFactory<Integer> valueFactory2;
  public SpinnerValueFactory<Integer> valueFactory3;
  public SpinnerValueFactory<Integer> valueFactory4;
  public SpinnerValueFactory<Integer> valueFactory5;
  public SpinnerValueFactory<Integer> valueFactory6;
  public MediaPlayer mediaPlayer;
  public MediaPlayer effectPlayer;
  public int templvl0;
  public int templvl1;
  public int templvl2;
  public int templvl3;
  public int templvl4;
  public int templvl5;
  public int templvl6;
  public int meteorstrike;
  public int for_one_turn_flag;
  public boolean show_data;
  public PathFinder finder;
  public HashMap<String, Integer> init;
  public HashMap<String, Integer> after;
  public int pathcost;
  public int totalcost;
  public int totalfoodcost;
  public int currentlvl;
  public Client client;


  Image redflag;
  Image blueflag;

  /*
   * this method initializes javaFX enviornment
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      redflag = new Image(getClass().getResource("IMG_3556.PNG").toURI().toString());
      blueflag = new Image(getClass().getResource("IMG_3555.PNG").toURI().toString());
      level6button.setDisable(true);
      init = new HashMap<String, Integer>(); // init and after used for upgrading units
      after = new HashMap<String, Integer>();
      meteorstrike = 0;
      for_one_turn_flag = 0; // run for one turn flag
      turnnum = 1; // current turn number
      show_data = false; // bool to show data on territories
      units = new HashMap<String, Integer>(); // used for attacking and moving holders
      activitylog.appendText("Turn " + turnnum + "\n");
      currentlvl = 1;
      client = new Client("localhost", "6666"); // initializes a client for each player
      finder = new PathFinder(client.world, client.player.getName());
      playername.setText(client.player.getName() + " Player");
      setlisteners(); // create listeners for spinners
      updateDisplayAndReset(); // reset display
      hideUpgradeButtons();
      disableRectangles();
      music();

      if (currentlvl == 1)
        upgradecostlabel.setText("Cost: " + 50);

      playertechlevel.setText("Tech Level: " + client.world.getTechLevel(client.player.getName()));
      scadrialsize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Scadrial").getSize()));
      gondorsize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Gondor").getSize()));
      ozsize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Oz").getSize()));
      narniasize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Narnia").getSize()));
      midkemiasize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Midkemia").getSize()));
      rosharsize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Roshar").getSize()));
      elantrissize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Elantris").getSize()));
      hogwartssize.setText("Size: " + Integer.toString(client.world.getTerritoryByName("Hogwarts").getSize()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    /*
     * this action shows data on each territory recs
     */
    showdatabutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        if (show_data == false) {
          showdata();
          show_data = true;
        } else {
          hidedata();
          show_data = false;
        }
      }
    });

    /*
     * each level button is used during the upgrade action to increase the level by
     * one
     */
    level0button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        if (templvl0 > 0) {
          templvl0 -= 1;
          templvl1 += 1;
          totalcost += 3;
          updateCostLabel2();
          updateUpgradeButtons();
        }
      }
    });

    level1button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        if (templvl1 > 0) {
          templvl1 -= 1;
          templvl2 += 1;
          totalcost += 8;
          updateCostLabel2();
          updateUpgradeButtons();
        }
      }
    });

    level2button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        if (templvl2 > 0) {
          templvl2 -= 1;
          templvl3 += 1;
          totalcost += 19;
          updateCostLabel2();
          updateUpgradeButtons();
        }
      }
    });

    level3button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (templvl3 > 0) {
          templvl3 -= 1;
          templvl4 += 1;
          totalcost += 25;
          updateCostLabel2();
          updateUpgradeButtons();
        }
      }
    });

    level4button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        if (templvl4 > 0) {
          templvl4 -= 1;
          templvl5 += 1;
          totalcost += 35;
          updateCostLabel2();
          updateUpgradeButtons();
        }
      }
    });

    level5button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        if (templvl5 > 0) {
          templvl5 -= 1;
          templvl6 += 1;
          totalcost += 50;
          updateCostLabel2();
          updateUpgradeButtons();
        }
      }
    });

    /*
     * instantiates the upgrading of units in a given territory
     */
    upgradebutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        promptlabel.setText("Which Territory's units would you like to upgrade?");
        upgradeflag = 1;
        enableRectangles();
        hideOptionButtons();
      }
    });

    /*
     * increases max tech cap for a player in one turn
     */
    upgradetlbutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        upgradeTechSound();
        if (for_one_turn_flag == 0) {
          currentlvl = client.world.getTechLevel(client.player.getName());
          Action constructed;
          try {
            constructed = new TechUpgradeAction(currentlvl);
            constructed.setPlayerName(client.player.getName());
            client.player.sendToServer(constructed);
            String msg = null;
            while (msg == null) {
              msg = client.player.receiveString();
            }
            if (msg.equals("Action successful!")) {
              activitylog.appendText("Upgrading TL at the end of this turn\n");
              constructed.doAction(client.world);
              updateDisplayAndReset();
            } else {
              System.out.println(msg);
              activitylog.appendText(msg + "\n");
              updateDisplayAndReset();
            }
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          for_one_turn_flag = 1;
        }
      }
    });

    /*
     * the following are when a user selects a territory for all territorys.
     * clicking on a territory can change the app's to, from, as well as selecting
     * which territory to upgrade units at
     */
    hogwarts.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Hogwarts";
          hogwartsselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Hogwarts";
          hogwartsselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Hogwarts";
              hogwartsselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Hogwarts";
              hogwartsselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Hogwarts";
              hogwartsselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Hogwarts";
              hogwartsselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });
    roshar.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Roshar";
          rosharselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Roshar";
          rosharselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Roshar";
              rosharselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              to = "Roshar";
              rosharselected.setOpacity(.3);
              tofromflag++;
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Roshar";
              rosharselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              to = "Roshar";
              rosharselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });

    scadrial.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Scadrial";
          scadrialselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Scadrial";
          scadrialselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Scadrial";
              scadrialselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Scadrial";
              scadrialselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Scadrial";
              scadrialselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Scadrial";
              scadrialselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });

    elantris.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Elantris";
          elantrisselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Elantris";
          elantrisselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Elantris";
              elantrisselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Elantris";
              elantrisselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Elantris";
              elantrisselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Elantris";
              elantrisselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });

    narnia.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Narnia";
          narniaselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Narnia";
          narniaselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Narnia";
              narniaselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Narnia";
              narniaselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              tofromflag++;
              from = "Narnia";
              narniaselected.setOpacity(.3);
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Narnia";
              narniaselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });
    midkemia.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Midkemia";
          midkemiaselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Midkemia";
          midkemiaselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              from = "Midkemia";
              midkemiaselected.setOpacity(.3);
              tofromflag++;
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Midkemia";
              midkemiaselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              from = "Midkemia";
              midkemiaselected.setOpacity(.3);
              tofromflag++;
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Midkemia";
              midkemiaselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });
    oz.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Oz";
          ozselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Oz";
          ozselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              from = "Oz";
              ozselected.setOpacity(.3);
              tofromflag++;
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Oz";
              ozselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              from = "Oz";
              ozselected.setOpacity(.3);
              tofromflag++;
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Oz";
              ozselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });

    gondor.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        buttonClickSound();
        if(meteorstrike == 1){
          from = "Gondor";
          gondorselected.setOpacity(.3);
          meteorstrikeHelper();
        }
        else if (upgradeflag == 1) {
          from = "Gondor";
          gondorselected.setOpacity(.3);
          promptlabel.setText("Which units would you like to upgrade in " + from + "?");
          updateAndShowUpgradeButtons();
        } else {
          if (attackmvflag == 0) {
            if (tofromflag == 0) {
              from = "Gondor";
              gondorselected.setOpacity(.3);
              tofromflag++;
              promptlabel.setText("Which territory would you like to move TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Gondor";
              gondorselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          } else {
            if (tofromflag == 0) {
              from = "Gondor";
              gondorselected.setOpacity(.3);
              tofromflag++;
              promptlabel.setText("Which territory would you like to attack TO?");
            } else if (tofromflag == 1) {
              tofromflag++;
              to = "Gondor";
              gondorselected.setOpacity(.3);
              updateSpinnerValueFactory();
              promptlabel.setText("Which units would you like to move?");
              setUpgradeSliders(true);
            } else if (tofromflag == 2) {
              tofromflag++;
            }
          }
        }
      }
    });

    /*
     * once a player is finished choosing how many units to move, this button will
     * construct the respective action and call its helper function
     */
    submitbutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        confirmSound();
        resetselected();
        ArrayList<Spinner<Integer>> spinners = new ArrayList<Spinner<Integer>>();
        spinners.add(level0slider);
        spinners.add(level1slider);
        spinners.add(level2slider);
        spinners.add(level3slider);
        spinners.add(level4slider);
        spinners.add(level5slider);
        spinners.add(level6slider);
        totalfoodcost = 0;
        for (int i = 0; i < 7; i++) {
          units.put("level" + i + " unit", (int) spinners.get(i).getValue());
          totalfoodcost += spinners.get(i).getValue();
          // increase total cost by how many units selected
        }
        disableRectangles();
        if (attackmvflag == 0) {
          moveHelper();
        } else {
          attackHelper();
        }
      }
    });

    /*
     * this button instantiates the attack phase of the game
     */
    attackbutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        enableRectangles();
        units = new HashMap<String, Integer>();
        hideOptionButtons();
        cancelbutton.setVisible(true);
        promptlabel.setText("Which territory would you like to attack FROM?");
        tofromflag = 0;
        attackmvflag = 1;
      }
    });

    /*
     * this button resets all current progress in any other action and returns user
     * to the begining of the game
     */
    cancelbutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        resetselected();
        disableRectangles();
        showOptionButtons();
        hideUpgradeButtons();
        narniabutton.setVisible(false);
        midkemiabutton.setVisible(false);
        ozbutton.setVisible(false);
        gondorbutton.setVisible(false);
        promptlabel.setText("Please select an action (to the right)");
        tofromflag = 0;
        for_one_turn_flag = 0;
        attackmvflag = 0;
        upgradeflag = 0;
        totalcost = 0;
        setUpgradeSliders(false);
      }
    });

    /*
     * this button instantiates the move action of the user
     */
    movebutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        enableRectangles();
        hideOptionButtons();
        units = new HashMap<String, Integer>();
        cancelbutton.setVisible(true);
        promptlabel.setText("Which territory would you like to move FROM?");
      }
    });

    /*
     * once a player is finishing making actions, pressing the DONE button will send
     * all actions to the server for processing
     */
    donebutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        turnnum += 1;
        for_one_turn_flag = 0; // reset the upgrade TL button
        Action constructed;
        try {
          constructed = new DoneAction();
          client.player.sendToServer(constructed);
          World temp = null;
          temp = client.player.receiveFromServer();
          client.updateClientWorld(temp);
          finder = new PathFinder(client.world, client.player.getName());
          updateDisplayAndReset();
          activitylog.clear();
          updateGloballog();
          playertechlevel.setText("Tech Level: " + client.world.getTechLevel(client.player.getName()));

          if (client.world.getTechLevel(client.player.getName()) == 1)
            upgradecostlabel.setText("Cost: " + 50);
          if (client.world.getTechLevel(client.player.getName()) == 2)
            upgradecostlabel.setText("Cost: " + 75);
          if (client.world.getTechLevel(client.player.getName()) == 3)
            upgradecostlabel.setText("Cost: " + 125);
          if (client.world.getTechLevel(client.player.getName()) == 4)
            upgradecostlabel.setText("Cost: " + 200);
          if (client.world.getTechLevel(client.player.getName()) == 5)
            upgradecostlabel.setText("Cost: " + 300);

          activitylog.appendText("----------TURN " + turnnum + "----------\n");
          checkWhoWon();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    });
    dragonstrikebutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        buttonClickSound();
        promptlabel.setText("Where would you like to call the Meteor shower?");
        meteorstrike = 1;
        enableRectangles();
        hideOptionButtons();
      }
    });

    /*
     * once a user is finished upgrading units, finish will send the after and
     * before data to the server for error handling and processing
     */
    finishbutton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        upgradeUnitSound();
        resetselected();
        after.put("level0 unit", templvl0);
        after.put("level1 unit", templvl1);
        after.put("level2 unit", templvl2);
        after.put("level3 unit", templvl3);
        after.put("level4 unit", templvl4);
        after.put("level5 unit", templvl5);
        after.put("level6 unit", templvl6);

        Action constructed;

        try {
          constructed = new UnitUpgradeAction(client.world.getTerritoryByName(from), init, after);
          constructed.setPlayerName(client.player.getName());
          client.player.sendToServer(constructed);
          showOptionButtons();
          cancelbutton.setVisible(false);
          promptlabel.setText("Please select an action (to the right)");
          tofromflag = 0;
          String msg = null;
          while (msg == null) {
            msg = client.player.receiveString();
          }
          if (msg.equals("Action successful!")) {
            activitylog.appendText("Upgrading units at " + from + " to:\n");
            printUpgredUnits();
            constructed.doAction(client.world);
            updateDisplayAndReset();
          } else {
            System.out.println(msg);
            activitylog.appendText(msg + "\n");
            updateDisplayAndReset();
          }
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    });

  }

  /*
   * sets listeners of the sliders for live time cost view back to the player
   */
  public void setlisteners() {
    level0slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level1slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level2slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level3slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level4slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level5slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level6slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
    level0slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });
    level1slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });

    level2slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });

    level3slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });

    level4slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });

    level5slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });

    level6slider.valueProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue > oldValue) {
        totalfoodcost++;
      } else {
        totalfoodcost--;
      }
      updateCostLabel();
    });
  }

  public void meteorstrikeHelper(){
    Action constructed;
    try {
      constructed = new MeteorShowerAction(from, 500);
      constructed.setPlayerName(client.player.getName());
      client.player.sendToServer(constructed);
      showOptionButtons();
      cancelbutton.setVisible(false);
      promptlabel.setText("Please select an action (to the right)");
      tofromflag = 0;
      meteorstrike = 0;
      String msg = null;
      while (msg == null) {
        msg = client.player.receiveString();
      }
      if (msg.equals("Action successful!")) {
        activitylog.appendText("You struck + " + from + ", killing all units!\n");
        constructed.doAction(client.world);
        updateDisplayAndReset();
      } else {
        System.out.println(msg);
        activitylog.appendText(msg + "\n");
        updateDisplayAndReset();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void showdata() {
    hideNameandSize();
    showDataLabels();
  }

  public void hidedata() {
    showNameandSize();
    hideDataLabels();
  }

  /*
   * helper functions to hide rectangles, labels, and buttons!
   */
  public void hideNameandSize() {
    ozlabel.setVisible(false);
    rosharlabel.setVisible(false);
    midkemialabel.setVisible(false);
    gondorlabel.setVisible(false);
    hogwartslabel.setVisible(false);
    scadriallabel.setVisible(false);
    elantrislabel.setVisible(false);
    narnialabel.setVisible(false);

    narniasize.setVisible(false);
    rosharsize.setVisible(false);
    midkemiasize.setVisible(false);
    elantrissize.setVisible(false);
    hogwartssize.setVisible(false);
    scadrialsize.setVisible(false);
    ozsize.setVisible(false);
    gondorsize.setVisible(false);

    ozcolor.setVisible(false);
    gondorcolor.setVisible(false);
    scadrialcolor.setVisible(false);
    hogwartscolor.setVisible(false);
    midkemiacolor.setVisible(false);
    rosharcolor.setVisible(false);
    narniacolor.setVisible(false);
    elantriscolor.setVisible(false);
  }

  public void showNameandSize() {
    ozlabel.setVisible(true);
    rosharlabel.setVisible(true);
    midkemialabel.setVisible(true);
    gondorlabel.setVisible(true);
    hogwartslabel.setVisible(true);
    scadriallabel.setVisible(true);
    elantrislabel.setVisible(true);
    narnialabel.setVisible(true);

    narniasize.setVisible(true);
    rosharsize.setVisible(true);
    midkemiasize.setVisible(true);
    elantrissize.setVisible(true);
    hogwartssize.setVisible(true);
    scadrialsize.setVisible(true);
    ozsize.setVisible(true);
    gondorsize.setVisible(true);

    ozcolor.setVisible(true);
    gondorcolor.setVisible(true);
    scadrialcolor.setVisible(true);
    hogwartscolor.setVisible(true);
    midkemiacolor.setVisible(true);
    rosharcolor.setVisible(true);
    narniacolor.setVisible(true);
    elantriscolor.setVisible(true);
  }

  public void resetselected(){
    narniaselected.setOpacity(0);
    ozselected.setOpacity(0);
    rosharselected.setOpacity(0);
    hogwartsselected.setOpacity(0);
    elantrisselected.setOpacity(0);
    scadrialselected.setOpacity(0);
    gondorselected.setOpacity(0);
    midkemiaselected.setOpacity(0);
  }

  public void showDataLabels() {
    ozdatalabel.setVisible(true);
    roshardatalabel.setVisible(true);
    midkemiadatalabel.setVisible(true);
    gondordatalabel.setVisible(true);
    hogwartsdatalabel.setVisible(true);
    scadrialdatalabel.setVisible(true);
    elantrisdatalabel.setVisible(true);
    narniadatalabel.setVisible(true);

    /*
     * data labels are updated on button press, and recieve information from the
     * client's world data
     */
    narniadatalabel.setText(client.world.getTerritoryByName("Narnia").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Narnia").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Narnia").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Narnia").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Narnia").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Narnia").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Narnia").getUnits("level6 unit") + " lvl6 unit");
    ozdatalabel.setText(client.world.getTerritoryByName("Oz").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Oz").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Oz").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Oz").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Oz").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Oz").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Oz").getUnits("level6 unit") + " lvl6 unit");
    roshardatalabel.setText(client.world.getTerritoryByName("Roshar").getUnits("level0 unit") + " lvl  unit  "
        + client.world.getTerritoryByName("Roshar").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Roshar").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Roshar").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Roshar").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Roshar").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Roshar").getUnits("level6 unit") + " lvl6 unit");
    scadrialdatalabel.setText(client.world.getTerritoryByName("Scadrial").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Scadrial").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Scadrial").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Scadrial").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Scadrial").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Scadrial").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Scadrial").getUnits("level6 unit") + " lvl6 unit");
    hogwartsdatalabel.setText(client.world.getTerritoryByName("Hogwarts").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Hogwarts").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Hogwarts").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Hogwarts").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Hogwarts").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Hogwarts").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Hogwarts").getUnits("level6 unit") + " lvl6 unit");
    elantrisdatalabel.setText(client.world.getTerritoryByName("Elantris").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Elantris").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Elantris").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Elantris").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Elantris").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Elantris").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Elantris").getUnits("level6 unit") + " lvl6 unit");
    gondordatalabel.setText(client.world.getTerritoryByName("Gondor").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Gondor").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Gondor").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Gondor").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Gondor").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Gondor").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Gondor").getUnits("level6 unit") + " lvl6 unit");
    midkemiadatalabel.setText(client.world.getTerritoryByName("Midkemia").getUnits("level0 unit") + " lvl0 unit  "
        + client.world.getTerritoryByName("Midkemia").getUnits("level1 unit") + " lvl1 unit\n"
        + client.world.getTerritoryByName("Midkemia").getUnits("level2 unit") + " lvl2 unit  "
        + client.world.getTerritoryByName("Midkemia").getUnits("level3 unit") + " lvl3 unit\n"
        + client.world.getTerritoryByName("Midkemia").getUnits("level4 unit") + " lvl4 unit  "
        + client.world.getTerritoryByName("Midkemia").getUnits("level5 unit") + " lvl5 unit\n"
        + client.world.getTerritoryByName("Midkemia").getUnits("level6 unit") + " lvl6 unit");
  }

  public void hideDataLabels() {
    ozdatalabel.setVisible(false);
    roshardatalabel.setVisible(false);
    midkemiadatalabel.setVisible(false);
    gondordatalabel.setVisible(false);
    hogwartsdatalabel.setVisible(false);
    scadrialdatalabel.setVisible(false);
    elantrisdatalabel.setVisible(false);
    narniadatalabel.setVisible(false);

    showNameandSize();
  }

  public void disableRectangles() {
    roshar.setDisable(true);
    hogwarts.setDisable(true);
    elantris.setDisable(true);
    oz.setDisable(true);
    midkemia.setDisable(true);
    narnia.setDisable(true);
    scadrial.setDisable(true);
    gondor.setDisable(true);
  }

  public void enableRectangles() {
    roshar.setDisable(false);
    hogwarts.setDisable(false);
    elantris.setDisable(false);
    oz.setDisable(false);
    midkemia.setDisable(false);
    narnia.setDisable(false);
    scadrial.setDisable(false);
    gondor.setDisable(false);
  }

  public void updateUpgradeButtons() {
    level0button.setText("Lvl0: " + templvl0 + " (cost: 3)");
    level1button.setText("Lvl1: " + templvl1 + " (cost: 8)");
    level2button.setText("Lvl2: " + templvl2 + " (cost: 19)");
    level3button.setText("Lvl3: " + templvl3 + " (cost: 25)");
    level4button.setText("Lvl4: " + templvl4 + " (cost: 35)");
    level5button.setText("Lvl5: " + templvl5 + " (cost: 50)");
    level6button.setText("Lvl6: " + templvl6);
  }

  public void updateAndShowUpgradeButtons() {
    templvl0 = client.world.getTerritoryByName(from).getUnits("level0 unit");
    templvl1 = client.world.getTerritoryByName(from).getUnits("level1 unit");
    templvl2 = client.world.getTerritoryByName(from).getUnits("level2 unit");
    templvl3 = client.world.getTerritoryByName(from).getUnits("level3 unit");
    templvl4 = client.world.getTerritoryByName(from).getUnits("level4 unit");
    templvl5 = client.world.getTerritoryByName(from).getUnits("level5 unit");
    templvl6 = client.world.getTerritoryByName(from).getUnits("level6 unit");

    init = client.world.getTerritoryByName(from).getUnits();

    level0button.setText("Lvl0: " + templvl0 + " (cost: 3)");
    level1button.setText("Lvl1: " + templvl1 + " (cost: 8)");
    level2button.setText("Lvl2: " + templvl2 + " (cost: 19)");
    level3button.setText("Lvl3: " + templvl3 + " (cost: 25)");
    level4button.setText("Lvl4: " + templvl4 + " (cost: 35)");
    level5button.setText("Lvl5: " + templvl5 + " (cost: 50)");
    level6button.setText("Lvl6: " + templvl6);
    showUpgradeButtons();
  }

  public void checkWhoWon() throws IOException {
    if (client.world.hasEnd() != null) {
      activitylog.appendText("The winner is " + client.world.hasEnd() + "\n");
      client.endGame();
    }
  }

  public void showUpgradeButtons() {
    level0button.setVisible(true);
    level1button.setVisible(true);
    level2button.setVisible(true);
    level3button.setVisible(true);
    level4button.setVisible(true);
    level5button.setVisible(true);
    level6button.setVisible(true);
    costlabel2.setVisible(true);

    cancelbutton.setVisible(true);
    finishbutton.setVisible(true);
  }

  public void hideUpgradeButtons() {
    level0button.setVisible(false);
    level1button.setVisible(false);
    level2button.setVisible(false);
    level3button.setVisible(false);
    level4button.setVisible(false);
    level5button.setVisible(false);
    costlabel2.setVisible(false);
    level6button.setVisible(false);
    cancelbutton.setVisible(false);
    finishbutton.setVisible(false);
  }

  public void updateSpinnerValueFactory() {
    level0slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level0 unit"), 0));
    level1slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level1 unit"), 0));
    level2slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level2 unit"), 0));
    level3slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level3 unit"), 0));
    level4slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level4 unit"), 0));
    level5slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level5 unit"), 0));
    level6slider.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
        client.world.getTerritoryByName(from).getUnits("level6 unit"), 0));
    if (client.world.getTerritoryByName(from).getPlayer().equals(client.world.getTerritoryByName(to).getPlayer())) {
      pathcost = finder.getshorttestCost(from, to);
    } else {
      pathcost = 1;
    }
    totalfoodcost = 0;
    updateCostLabel();
  }

  /*
   * uses spinner values to send a constructed action to the server
   */
  public void attackHelper() {
    Action constructed;
    try {
      constructed = new AttackAction(units, from, to, pathcost * totalfoodcost);
      constructed.setPlayerName(client.player.getName());
      client.player.sendToServer(constructed);
      showOptionButtons();
      cancelbutton.setVisible(false);
      promptlabel.setText("Please select an action (to the right)");
      tofromflag = 0;
      String msg = null;
      while (msg == null) {
        msg = client.player.receiveString();
      }
      if (msg.equals("Action successful!")) {
        activitylog.appendText("Attacking " + to + " from " + from + " using:\n");
        printUnitsMovedAttacked();
        constructed.doAction(client.world);
        updateDisplayAndReset();
      } else {
        System.out.println(msg);
        activitylog.appendText(msg + "\n");
        updateDisplayAndReset();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void hideOptionButtons() {
    attackbutton.setVisible(false);
    movebutton.setVisible(false);
    donebutton.setVisible(false);
    upgradebutton.setVisible(false);
    upgradetlbutton.setVisible(false);
    dragonstrikebutton.setVisible(false);
    dragonstrikecost.setVisible(false);
    upgradecostlabel.setVisible(false);
  }

  public void showOptionButtons() {
    attackbutton.setVisible(true);
    movebutton.setVisible(true);
    donebutton.setVisible(true);
    upgradebutton.setVisible(true);
    upgradetlbutton.setVisible(true);
    if (client.world.getTechLevel(client.player.getName()) == 6) {
      dragonstrikebutton.setVisible(true);
      dragonstrikecost.setVisible(true);
    }
    upgradecostlabel.setVisible(true);
  }

  /*
   * similar to the attackhelper, move helper also uses spinner values to send to
   * server
   */
  public void moveHelper() {
    try {
      Action constructed = new MoveAction(units, from, to, pathcost * totalfoodcost);
      constructed.setPlayerName(client.player.getName());
      client.player.sendToServer(constructed);
      showOptionButtons();
      cancelbutton.setVisible(false);
      promptlabel.setText("Please select an action (to the right)");
      tofromflag = 0;
      String msg = null;
      while (msg == null) {
        msg = client.player.receiveString();
      }
      if (msg.equals("Action successful!")) {
        activitylog.appendText("Moving to  " + to + " from " + from + " using:\n");
        printUnitsMovedAttacked();
        constructed.doAction(client.world);
        updateDisplayAndReset();
      } else {
        System.out.println(msg);
        activitylog.appendText(msg + "\n");
        updateDisplayAndReset();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void setColor(ImageView currentFlag, String color) throws URISyntaxException {
    if (color.equals("Red")) {
      currentFlag.setImage(redflag);
    } else {
      currentFlag.setImage(blueflag);
    }
  }

  public void setActivityLog(String text) {
    activitylog.appendText(text + "\n");
  }

  public void setUpgradeSliders(Boolean status) {
    level0slider.setVisible(status);
    level1slider.setVisible(status);
    level2slider.setVisible(status);
    level3slider.setVisible(status);
    level4slider.setVisible(status);
    level5slider.setVisible(status);
    level6slider.setVisible(status);

    level0label.setVisible(status);
    level1label.setVisible(status);
    level2label.setVisible(status);
    level3label.setVisible(status);
    level4label.setVisible(status);
    level5label.setVisible(status);
    level6label.setVisible(status);

    level0picture.setVisible(status);
    level1picture.setVisible(status);
    level2picture.setVisible(status);
    level3picture.setVisible(status);
    level4picture.setVisible(status);
    level5picture.setVisible(status);
    level6picture.setVisible(status);

    submitbutton.setVisible(status);
    costlabel.setVisible(status);
  }

  public void updateCostLabel() {
    costlabel.setText("Cost: " + (pathcost * totalfoodcost));
  }

  public void updateCostLabel2() {
    costlabel2.setText("Cost: " + totalcost);
  }

  public void printUpgredUnits() {
    activitylog.appendText("Level0 units: " + after.get("level0 unit") + "\n");
    activitylog.appendText("Level1 units: " + after.get("level1 unit") + "\n");
    activitylog.appendText("Level2 units: " + after.get("level2 unit") + "\n");
    activitylog.appendText("Level3 units: " + after.get("level3 unit") + "\n");
    activitylog.appendText("Level4 units: " + after.get("level4 unit") + "\n");
    activitylog.appendText("Level5 units: " + after.get("level5 unit") + "\n");
    activitylog.appendText("Level6 units: " + after.get("level6 unit") + "\n");
  }

  public void printUnitsMovedAttacked() {
    activitylog.appendText("Level0 units: " + units.get("level0 unit") + "\n");
    activitylog.appendText("Level1 units: " + units.get("level1 unit") + "\n");
    activitylog.appendText("Level2 units: " + units.get("level2 unit") + "\n");
    activitylog.appendText("Level3 units: " + units.get("level3 unit") + "\n");
    activitylog.appendText("Level4 units: " + units.get("level4 unit") + "\n");
    activitylog.appendText("Level5 units: " + units.get("level5 unit") + "\n");
    activitylog.appendText("Level6 units: " + units.get("level6 unit") + "\n");
  }

  public ImageView getTerrByName(String territoryName) {
    if (territoryName.equals("Narnia")) {
      return narniacolor;
    }
    if (territoryName.equals("Scadrial")) {
      return scadrialcolor;
    }
    if (territoryName.equals("Roshar")) {
      return rosharcolor;
    }
    if (territoryName.equals("Hogwarts")) {
      return hogwartscolor;
    }
    if (territoryName.equals("Midkemia")) {
      return midkemiacolor;
    }
    if (territoryName.equals("Oz")) {
      return ozcolor;
    }
    if (territoryName.equals("Gondor")) {
      return gondorcolor;
    }
    if (territoryName.equals("Elantris")) {
      return elantriscolor;
    }
    return null;
  }

  /*
   * this method updates the global activity log which will send to both players
   * after each have pressed DONE
   */
  public void updateGloballog() {
    for (int i = 0; i < client.world.getActivityLog().size(); i++) {
      activitylog.appendText(client.world.getActivityLog().get(i));
    }
  }

  /*
   * this function resets the total display for a user interupting all data
   * collection and views
   */
  public void updateDisplayAndReset() throws URISyntaxException {
    init.clear();
    after.clear();
    units.clear();
    resetselected();
    upgradeflag = 0;
    totalfoodcost = 0;
    for_one_turn_flag = 0;
    attackmvflag = 0;
    totalcost = 0;
    playerresources.setText("Electricity: " + client.world.getResources(client.player.getName()).get("Electricity"));
    foodresources.setText("Food: " + client.world.getResources(client.player.getName()).get("Food"));

    activitylog.appendText("-----------------------\n");
    HashMap<String, Rectangle> mapping = new HashMap<String, Rectangle>();
    mapping.put("Narnia", narnia);
    mapping.put("Oz", oz);
    mapping.put("Roshar", roshar);
    mapping.put("Scadrial", scadrial);
    mapping.put("Hogwarts", hogwarts);
    mapping.put("Elantris", elantris);
    mapping.put("Gondor", gondor);
    mapping.put("Midkemia", midkemia);

    setUpgradeSliders(false);
    hideUpgradeButtons();
    showOptionButtons();

    for (Territory t : client.world.getTerritories()) {
      if (mapping.get(t.getName()) == null) {
      } else if (t.getPlayer().equals("Blue")) {
        setColor(getTerrByName(t.getName()), "Blue");
        mapping.get(t.getName()).setFill(Color.valueOf("#cbe6ff"));
      } else if (t.getPlayer().equals("Red")) {
        setColor(getTerrByName(t.getName()), "Red");
        mapping.get(t.getName()).setFill(Color.valueOf("#c8ffcc"));
      }
    }
    promptlabel.setText("Please select an action (to the right)");
  }

  public void music() {
    String s = "The_Saga_Begins.wav";
    Media media = null;
    try {
      media = new Media(getClass().getResource(s).toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(0.6);
    mediaPlayer.setOnEndOfMedia(new Runnable() {
      @Override
      public void run() {
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
      }
    });
    mediaPlayer.play();
  }

  public void confirmSound() {
    String s = "War-horn-sound.wav";
    Media media = null;
    try {
      media = new Media(getClass().getResource(s).toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    effectPlayer = new MediaPlayer(media);
    effectPlayer.play();
  }

  public void buttonClickSound() {
    String s = "button_click.wav";
    Media media = null;
    try {
      media = new Media(getClass().getResource(s).toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    effectPlayer = new MediaPlayer(media);
    effectPlayer.play();
  }

  public void upgradeUnitSound() {
    String s = "sword.wav";
    Media media = null;
    try {
      media = new Media(getClass().getResource(s).toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    effectPlayer = new MediaPlayer(media);
    effectPlayer.play();
  }

  public void upgradeTechSound() {
    String s = "war_drum.wav";
    Media media = null;
    try {
      media = new Media(getClass().getResource(s).toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    effectPlayer = new MediaPlayer(media);
    effectPlayer.play();
  }
}

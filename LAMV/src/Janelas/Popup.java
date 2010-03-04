/*
 * Popup.java
 *
 * Created on 17 de Junho de 2005, 17:03
 */

package Janelas;
import java.awt.*;
/**
 *
 * @author Barnes
 */  

// This appears in Core Web Programming from
// Prentice Hall Publishers, and may be freely used
// or adapted. 1997 Marty Hall, hall@apl.jhu.edu.

/** A pseudo-popup-menu to use in Java 1.0, which
 *  doesn't have a real one. It is really a listbox
 *  in a borderless window, with mouse tracking so
 *  it is closed when you move off it.
 */

public class Popup extends Window {
  private Component source;
  private List list;

  public Popup(Frame source) { 
    super(source);
    resize(100, 100);
    this.source = source;
  }

  public void display(String[] labels, int x, int y) {
    list = new List();
    for(int i=0; i<labels.length; i++)
      list.addItem(labels[i]);
    list.select(0);
    add("Center", list);
    pack();
    move(source.bounds().x + x,
         source.bounds().y + y);
    show();
  }

  /** Clicking an entry or moving off the menu
   *  counts as activating the menu.
   */
  
  public boolean handleEvent(Event event) {
    switch(event.id) {
      case Event.ACTION_EVENT:
      case Event.LIST_SELECT:
      case Event.MOUSE_EXIT:
        doAction();
        dispose();
        return(true);
      default: return(super.handleEvent(event));
    }
  }

  /** This is the method you override in subclasses
   *  to put the action that should be taken
   *  when a menu entry is selected. Use
   *  getList().getSelectedItem() or
   *  getList.getSelectedIndex() to determine which
   *  entry was chosen.
   */
  
  public void doAction() {} // override in subclass

  protected List getList() {
    return(list);
  }
}

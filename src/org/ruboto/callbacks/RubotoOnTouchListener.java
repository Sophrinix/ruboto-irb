package org.ruboto.callbacks;

import org.jruby.Ruby;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.exceptions.RaiseException;
import org.ruboto.Script;

public class RubotoOnTouchListener implements android.view.View.OnTouchListener {
  private Ruby __ruby__;

  public static final int CB_TOUCH = 0;
  private IRubyObject[] callbackProcs = new IRubyObject[1];



  private Ruby getRuby() {
    if (__ruby__ == null) __ruby__ = Script.getRuby();
    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public boolean onTouch(android.view.View v, android.view.MotionEvent event) {
    if (callbackProcs[CB_TOUCH] != null) {
      try {
        return (Boolean)RuntimeHelpers.invoke(getRuby().getCurrentContext(), callbackProcs[CB_TOUCH], "call" , JavaUtil.convertJavaToRuby(getRuby(), v), JavaUtil.convertJavaToRuby(getRuby(), event)).toJava(boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return false;
    }
  }
}

public final class OttoResponses {
    private OttoResponses() {
    }

    /////////////////
    //// Symbols ////
    /////////////////
    public static final String owl = "            z\n"
            + "          z\n"
            + "   ^_^  z\n"
            + "  (-,-)  \n"
            + "  { \" }  \n"
            + "---\"-\"---\n";
    public static final String line = "____________________________________________________________\n";

    /////////////////
    //// Actions  ///
    /////////////////
    public static final String intro = "\nOtto would rather be napping, \nbut he suppose he can help you with your tasks.\n";
    public static final String bye = "Otto is signing off now. Don't wake him up again.";
    public static final String addTask = "More work? Otto has noted it down, but he'd much rather be sleeping.\n";
    public static final String numOfTasks = "\nNow you have %d task(s) in the list.";
    public static final String deleteTask = "Finally, something Otto can get behind—deleting a task. It's gone now, just like Otto wishes he could be... back to his nap.\n";
    public static final String complete = "Well, finally. You finished something.\n";
    public static final String incomplete = "So you didn't finish that task. Try to get it done so Otto can rest easy.\n";
    public static final String showList = "Here are the tasks in your list. Otto would rather be asleep than dealing with all this.";

    /////////////////
    //// Errors /////
    /////////////////
    public static final String unknownCommandError = "Otto doesn't recognize this command. Speak English.";
    public static final String indexError = "You need to give Otto an index that is in the list. Can't you even count?";
    public static final String deleteError = "You need to tell Otto what task you want to delete. Not like Otto cares though.";
    public static final String markError = "You need to tell Otto what task you want to mark. Not like Otto cares though.";
    public static final String eventError = "The format for event is wrong. Missing description, start time or end time.";
    public static final String deadlineError = "The format for deadline is wrong. Missing description or deadline.";
    public static final String todoError = "Todo must contain a description.";
}

import java.util.Stack;

public class Code071_SimplifyPath {
    public String simplifyPath(String path) {
        String ans = "";
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < paths.length; i++) {
            if (!stack.isEmpty() && paths[i].equals("..")) {
                stack.pop();
            } else if (!paths[i].equals(".") && !paths[i].equals("") && !paths[i].equals("..")) {
                stack.push(paths[i]);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            ans = "/" + stack.pop() + ans;
        }
        return ans;
    }
}

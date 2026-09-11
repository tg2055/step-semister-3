class AccessChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
                return "DENIED";
            }

            return "DENIED";
        }

        return "DENIED";
    }
}

public class Q2_SubclassTicketAccess {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}
package api;

import models.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryManager {
    private final List<Category> categories;

    public CategoryManager(){
        this.categories = new ArrayList<>();
        categories.add(new Category("Φρέσκα τρόφιμα", Arrays.asList("Φρούτα","Λαχανικά")));
        categories.add(new Category("Κατεψυγμένα τρόφιμα", Arrays.asList("Κατεψυγμένα λαχανικά", "Κατεψυγμένα κρέατα", "Κατεψυγμένες πίτσες", "Κατεψυγμένα γεύματα")));
        categories.add(new Category("Προϊόντα ψυγείου", Arrays.asList("Τυριά", "Γιαούρτια", "Γάλα", "Βούτυρο")));
        categories.add(new Category("Αλλαντικά", Arrays.asList("Ζαμπόν", "Σαλάμι", "Μπέικον")));
        categories.add(new Category("Αλκοολούχα ποτά", Arrays.asList("Μπύρα", "Κρασί", "Ούζο", "Τσίπουρο")));
        categories.add(new Category("Μη αλκοολούχα ποτά", Arrays.asList("Χυμοί", "Αναψυκτικά", "Νερό", "Ενεργειακά ποτά")));
        categories.add(new Category("Καθαριστικά για το σπίτι", Arrays.asList("Καθαριστικά για το πάτωμα", "Καθαριστικά για τα τζάμια", "Καθαριστικά κουζίνας")));
        categories.add(new Category("Απορρυπαντικά ρούχων", Arrays.asList("Σκόνες πλυντηρίου", "Υγρά πλυντηρίου", "Μαλακτικά")));
        categories.add(new Category("Καλλυντικά", Arrays.asList("Κρέμες προσώπου", "Μακιγιάζ", "Λοσιόν σώματος")));
        categories.add(new Category("Προϊόντα στοματικής υγιεινής", Arrays.asList("Οδοντόκρεμες", "Οδοντόβουρτσες", "Στοματικά διαλύματα")));
        categories.add(new Category("Πάνες", Arrays.asList("Πάνες για μωρά", "Πάνες ενηλίκων")));
        categories.add(new Category("Δημητριακά", Arrays.asList("Νιφάδες καλαμποκιού", "Μούσλι", "Βρώμη")));
        categories.add(new Category("Ζυμαρικά", Arrays.asList("Μακαρόνια", "Κριθαράκι", "Ταλιατέλες")));
        categories.add(new Category("Σνακ", Arrays.asList("Πατατάκια", "Κράκερς", "Μπάρες δημητριακών")));
        categories.add(new Category("Έλαια", Arrays.asList("Ελαιόλαδο", "Ηλιέλαιο", "Σογιέλαιο")));
        categories.add(new Category("Κονσέρβες", Arrays.asList("Κονσέρβες ψαριών", "Κονσέρβες λαχανικών", "Κονσέρβες φρούτων")));
        categories.add(new Category("Χαρτικά", Arrays.asList("Χαρτί υγείας", "Χαρτοπετσέτες", "Χαρτομάντηλα")));
    }

    public List<Category> getCategories(){return categories;}

    public Category getCategoryByTitle(String title){
        for(Category category: categories){
            if(title.equals(category.getTitle())){
                return category;
            }
        }
        return null;
    }
}

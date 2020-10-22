package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.model.Model;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.ReadOnlyUserPrefs;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.budget.UniqueCategoryBudgetList;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;

public class TopupCommandTest {

    @Test
    public void constructor_nullAmount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TopupCommand(null));
    }

    @Test
    void execute_amountAddedToModel_success() {
        ModelStub modelStub = new ModelStub();
        Amount validAmount = new Amount("1");
        CommandResult commandResult = new TopupCommand(validAmount).execute(modelStub);
        assertEquals(String.format(TopupCommand.MESSAGE_SUCCESS, validAmount.asDouble()),
                commandResult.getFeedbackToUser());
        assertEquals(validAmount, modelStub.budgets.getAmount());
    }

    @Test
    void equals() {
        Amount toAddOne = new Amount("1");
        Amount toAddTwo = new Amount("2");
        TopupCommand topupCommandOne = new TopupCommand(toAddOne);
        TopupCommand topupCommandTwo = new TopupCommand(toAddTwo);

        // same object -> returns true
        assertTrue(topupCommandOne.equals(topupCommandOne));

        // same values -> returns true
        TopupCommand topupOneCopy = new TopupCommand(toAddOne);
        assertTrue(topupCommandOne.equals(topupOneCopy));

        // different types -> returns false
        assertFalse(topupCommandOne.equals(1));

        // null -> returns false
        assertFalse(topupCommandOne.equals(null));

        // different amount -> returns false
        assertFalse(topupCommandOne.equals(topupCommandTwo));
    }

    /**
     * A Model stub with a budget that can be topped up.
     */
    private class ModelStub implements Model {

        final UniqueCategoryBudgetList budgets;

        ModelStub() {
            budgets = new UniqueCategoryBudgetList();
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getExpenseBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseBookFilePath(Path expenseBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseBook(ReadOnlyExpenseBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExpenseBook getExpenseBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpense(Expense target, Expense editedExpense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expense> getFilteredExpenseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Budget getTotalBudget() {
            return budgets;
        }

        @Override
        public void topupBudget(Amount amount) {
            budgets.topupBudget(amount);
        }
    }
}
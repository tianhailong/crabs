package com.code.crabs.jdbc.compiler.extension.clause;

import com.code.crabs.jdbc.lang.Expression;
import com.code.crabs.jdbc.lang.extension.clause.HavingClause;
import com.code.crabs.jdbc.compiler.GrammarAnalyzer.ClauseGrammarAnalyzer;

import java.sql.SQLException;

public final class HavingClauseGrammarAnalyzer extends ClauseGrammarAnalyzer {

    public HavingClauseGrammarAnalyzer() {
        super(HavingClause.PREFIX_KEYWORD_LIST);
    }

    @Override
    protected final HavingClause doAnalyze(final GrammarAnalyzeContext context) throws SQLException {
        final int currentTokenStartPosition = context.currentTokenStartPosition();
        final Expression expression = ExpressionGrammarAnalyzer.analyze(context);
        if (expression == null) {
            throw newSQLException(context, "Expect an expression.", currentTokenStartPosition);
        }
        return new HavingClause(expression);
    }

}
